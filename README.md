## Digital Transformation ##
This repository has the microservices that were created during the course "Spring Cloud Eureka Microservices" by Andres Jose Guzman". With some small improvements, using Java 17, lombok, etc. Using the shared database pattern with MySQL. In addition to Generate common generic libraries for entities, services and controllers.

USE:

> spring-boot-starter-web

> spring-boot-devtools

> lombok

> spring-boot-starter-validation

> spring-boot-starter-data-jpa

> mysql-connector-java

> spring-cloud-starter-netflix-eureka-client

> spring-cloud-starter-netflix-eureka-server

## Architecture ##
The following architecture shows how this microservices project works.
![Alt text](/imgs/architecture-course-real.png "")

In each microservice it has a configuration similar to the following, only the name changes, which is the identifier of the microservice.
```
spring.application.name=cursos-microservice
server.port=${PORT:0}
eureka.instance.instance-id=${spring.application.name}:${random.value}

eureka.client.service-url.defaultZone=http://localhost:8761/eureka


spring.datasource.url=jdbc:mysql://localhost:3306/curso_ajg
spring.datasource.username=bmb
spring.datasource.password=admin123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.generate-ddl=true
logging.level.org.hibernate.SQL=debug
```

The eureka server configuration

```
spring.application.name=eureka-microservice
server.port=8761

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

And from the API Gateway that can be with zuul or spring cloud:

```
spring.application.name=microservicio-gateway
server.port=8090

eureka.client.service-url.defaultZone=http://localhost:8761/eureka

spring.cloud.gateway.routes[0].id=user-microservice
spring.cloud.gateway.routes[0].uri=lb://user-microservice
spring.cloud.gateway.routes[0].predicates=Path=/api/students/**
spring.cloud.gateway.routes[0].filters=StripPrefix=2

spring.cloud.gateway.routes[1].id=cursos-microservice
spring.cloud.gateway.routes[1].uri=lb://cursos-microservice
spring.cloud.gateway.routes[1].predicates=Path=/api/cursos/**
spring.cloud.gateway.routes[1].filters=StripPrefix=2

spring.cloud.gateway.routes[2].id=exams-microservice
spring.cloud.gateway.routes[2].uri=lb://exams-microservice
spring.cloud.gateway.routes[2].predicates=Path=/api/exams/**
spring.cloud.gateway.routes[2].filters=StripPrefix=2

spring.cloud.gateway.routes[3].id=answers-microservice
spring.cloud.gateway.routes[3].uri=lb://answers-microservice
spring.cloud.gateway.routes[3].predicates=Path=/api/answers/**
spring.cloud.gateway.routes[3].filters=StripPrefix=2

spring.cloud.loadbalancer.ribbon.enabled=false

```

```
spring.application.name=zuul-microservice
server.port=8090

eureka.client.service-url.defaultZone=http://localhost:8761/eureka

zuul.routes.usuarios.service-id=user-microservice
zuul.routes.usuarios.path=/api/students/**

zuul.routes.cursos.service-id=cursos-microservice
zuul.routes.cursos.path=/api/cursos/**

zuul.routes.exams.service-id=exams-microservice
zuul.routes.exams.path=/api/exams/**

zuul.routes.answers.service-id=answers-microservice
zuul.routes.answers.path=/api/answers/**
```

Load balancing is also taken into account when raising instances of the same microservice, this through cloud gateway allows us to use them equitably. If there is latency or it is not available, it will select the best one.

IMPORTANT, ALL THIS HAS MANY MORE CONFIGURATIONS. I TRY TO SUMMARIZE AND PUT KEY POINTS. TO KNOW BETTER, IT IS RECOMMENDED TO TAKE THE COURSE THAT IS IN UDEMY

## commons libraries ##
Three projects were created that are used as generic libraries. Where the main objective is to store common services and controllers that have the normal logic of creating, deleting and listing. Generating a good practice.
![Alt text](/imgs/1.png "")

###Service###
```
//De esta forma se tiene una clase generica, se hace dinamica y se puede reutilizar para cualquier entidad.
//Esto no tiene relacion con microservicios solamente se esta generando una buena practica
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {
    
    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }
    
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
```
###Controller###
```
//Solo se esta usando programacion orientada a objectos. EL api generic
public class CommonController<E, S extends CommonService<E>> {
    
    @Autowired
    protected S service;
    
    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @GetMapping("/page")
    public ResponseEntity<?> list(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<E> s = service.findById(id);
        
        if(s.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        
        return ResponseEntity.ok(s);
    }
    
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result){
    	
    	if(result.hasErrors()) {
    		return this.validate(result);
    	}
    	
        E entityDB = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
    
    protected ResponseEntity<?> validate(BindingResult result){
    	Map<String, Object> errors = new HashMap<>();
    	
    	result.getFieldErrors().forEach(err->{
    		errors.put(err.getField(), "The field " + err.getField() + " " +err.getDefaultMessage());
    	});
    	return ResponseEntity.badRequest().body(errors);
    }
}
```


