package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/test")
public class GreetingController {

	 private static Map<String, Product> productRepo = new HashMap<>();
	   static {
	      Product honey = new Product();
	      honey.setId("1");
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);
	      
	      Product almond = new Product();
	      almond.setId("2");
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);
	   }
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
//    @Value("${com.didispace.blog.name}")
//    private  String app_pro;

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//    	System.out.println(this.app_pro);
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, this.app_pro));
//    }
    @GetMapping("/products")
    public ResponseEntity<Object> getProduct(){
//    	System.out.println(4/0);
    	return new ResponseEntity<Object>(productRepo.values(), HttpStatus.OK);
    }
//    
//    @RequestMapping(value="/products/{id}",method=RequestMethod.PUT)
//    public ResponseEntity<Object> updateProduct(@PathVariable String id){
//    	System.out.println(id);
//        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
//
//    }
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
//    	System.out.println("###########"+id);
//    	return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
//    }
    @RequestMapping(value = "/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
    	System.out.println("###########"+id);
        if(!productRepo.containsKey(id))throw new ProductNotFoundException();
    	return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }
////    @PostMapping("/conveyProducet")
//    @GetMapping("/conveyProducet")
    @RequestMapping(value = "/conveyproducts")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
    	System.out.println(product);
        productRepo.put(product.getId(), product);
        return new  ResponseEntity<Object>("Product is created successfully!",HttpStatus.CREATED);
        
    }
    @RequestMapping(value = "/request")
    public ResponseEntity<Object> requestProduct(@RequestParam("id") String id){
    	System.out.println("in the controller:"+id);
//    	System.out.println("in the controller:"+name);
//    	productRepo.put(product.getId(), product);
    	return new  ResponseEntity<Object>("Product is created successfully!",HttpStatus.CREATED);
    	
    }
//    
    //form.serialize
//    @RequestMapping(value = "/products1", method = RequestMethod.POST)
//    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
//    	System.out.println(product);
//       productRepo.put(product.getId(), product);
//       return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
//    }
    
}