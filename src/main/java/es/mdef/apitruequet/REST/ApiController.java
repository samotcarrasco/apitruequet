package es.mdef.apitruequet.REST;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class ApiController {

	static final String HOST = "https://truequet-carrascodim.b4a.run/api/";
	
    @Autowired
    private Set<Object> controllers;

    @GetMapping
    @ResponseBody
public ResponseEntity<Map<String, String>> getServiceList() {
    Map<String, String> services = new HashMap<>();
    for (Object controller : controllers) {          
        Class<?> controllerClass = controller.getClass();
        RestController annotation = AnnotationUtils.findAnnotation(controllerClass, RestController.class);
        if (annotation != null) {
            String entidad =  controller.getClass().getName().contains("Material") 
                    ? controller.getClass().getSimpleName().replace("Controller", "") + "es".toLowerCase() 
                    : controller.getClass().getSimpleName().replace("Controller", "") + "s".toLowerCase();
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                if (isServiceMethod(method) && method.toString().contains(controller.getClass().getSimpleName())) {
                    System.out.println("metodo   " + method + " entidad " + entidad);
                    String methodName = method.getName();
                    services.put(entidad + " servicio -> " + methodName, HOST + entidad.toLowerCase() + getEndpointUrl(controllerClass, method));
                }
            }
        }
    }

    Map<String, String> orderedServices = services.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    return ResponseEntity.ok(orderedServices);
}

    private boolean isServiceMethod(Method method) {
        return method.getDeclaringClass() != Object.class && method.isAnnotationPresent(GetMapping.class);
    }

    private String getEndpointUrl(Class<?> controllerClass, Method method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping annotation = method.getAnnotation(GetMapping.class);
            String[] paths = annotation.value();
            if (paths.length > 0) {
                return paths[0];
            }
        }
        return "";
	
    }
}
