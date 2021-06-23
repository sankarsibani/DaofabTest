package com.daofab.service;

import com.daofab.model.Child;
import com.daofab.model.Parent;

import java.util.List;

public interface DaofabService {
    public List<Parent> getParents(int page, String sortOrder);
    public List<Child> getChildrenByParentId(Long parentId);
}
