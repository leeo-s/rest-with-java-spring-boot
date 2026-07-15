package br.com.leeo_s.rest_with_java_spring_boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestLogController {

    private final Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog() {
        //os logs so irão aparecer conforme o nível de configuração aplicado no application.yml
        //onde colocamos o nível de DEBUG e irá aparecer todos, mas se mudarmos para
        //WARN por exemplo, somente o log de WARN e ERROR irão aparecer
        logger.debug("This is an DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is an WARN log");
        logger.error("This is an ERROR log");
        return "Logs generated successfully!";
    }
}
