package com.daofab.dao;

import com.daofab.model.Child;
import com.daofab.model.Parent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class Database {

    private static final Logger log = LoggerFactory.getLogger(Database.class);

    private List<Parent> parents;
    private List<Child> children;

    /**
     * loads all parent and children data
     */
    @PostConstruct
    public void initializeObjects() {
        log.info("Loading Parent and Children details");
        parents = getParents("Parent.json");
        children = getChildren("Child.json");
        log.info("Completed loading Parent and Children details");
    }


    private List<Parent> getParents(String fileName) {
        String content = getContent(fileName);
        Type type = new TypeToken<HashMap<String, List<Parent>>>() {
        }.getType();
        Gson gson = new Gson();
        Map<String, List<Parent>> parentsData = gson.fromJson(content, type);
        return parentsData.get("data");
    }

    private List<Child> getChildren(String fileName) {
        String content = getContent(fileName);
        Type type = new TypeToken<HashMap<String, List<Child>>>() {
        }.getType();
        Gson gson = new Gson();
        Map<String, List<Child>> childrenData = gson.fromJson(content, type);
        return childrenData.get("data");
    }

    private String getContent(String fileName) {
        String content = null;
        try {
            ClassLoader classLoader = Database.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException io) {
            io.printStackTrace();
        }
        return content;
    }

    /**
     * @return
     */
    public List<Parent> getParents() {
        return parents;
    }

    /**
     * @return
     */
    public List<Child> getChildren() {
        return children;
    }

    /**
     * @param parentId
     * @return
     */
    public List<Child> getChildrenByParentId(Long parentId) {
        return children.stream().filter(child -> child.getParentId().equals(parentId)).collect(Collectors.toList());
    }
}
