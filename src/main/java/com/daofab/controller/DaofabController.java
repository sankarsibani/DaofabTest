package com.daofab.controller;

import com.daofab.model.Child;
import com.daofab.model.Parent;
import com.daofab.service.DaofabService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DaofabController {

    private static final Logger log = LoggerFactory.getLogger(DaofabController.class);


    @Autowired
    private DaofabService daofabService;

    /**
     * @param page
     * @param sortOrder
     * @return takes the page(start value 1) and sortOrder(asc/desc), and returns data
     */
    @GetMapping("/parents")
    public ResponseEntity<List<Parent>> getParents(@RequestParam("page") Integer page, @RequestParam("sortOrder") String sortOrder) {
        log.info("getParents : Started pulling data for request");
        List<Parent> result = daofabService.getParents(page, sortOrder);
        if (!result.isEmpty()) {
            log.info("getParents : Found data for request");
            throw new RuntimeException();
            //return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("getParents : No Records found for the request");
            Map<String, Object> msg = new HashMap<>();
            msg.put("msg", "No data found");
            msg.put("code", 2000);
            return new ResponseEntity(msg, HttpStatus.OK);
        }
    }

    /**
     * @param parentId
     * @return takes parentId and returns children data
     */
    @GetMapping("/child/{parentId}")
    public ResponseEntity<List<Child>> getChildren(@PathVariable("parentId") Long parentId) {
        log.info("getChildren : Started pulling data for request");
        List<Child> result = daofabService.getChildrenByParentId(parentId);
        if (!result.isEmpty()) {
            log.info("getChildren : Found data for request");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            log.info("getChildren : No Records found for the request");
            Map<String, Object> msg = new HashMap<>();
            msg.put("msg", "No data found");
            msg.put("code", 2000);
            return new ResponseEntity(msg, HttpStatus.OK);
        }
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Map<String, Object>> handleException() {
        Map<String, Object> errorMsg = new HashMap<>();
        errorMsg.put("error", "Internal Error, Please contact Admin for more details");
        errorMsg.put("code", 5000);
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
