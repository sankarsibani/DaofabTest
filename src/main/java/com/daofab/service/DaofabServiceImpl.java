package com.daofab.service;

import com.daofab.dao.Database;
import com.daofab.model.Child;
import com.daofab.model.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @DaofabServiceImpl
 */
@Service
public class DaofabServiceImpl implements DaofabService {

    private static final Logger log = LoggerFactory.getLogger(DaofabServiceImpl.class);

    @Autowired
    private Database database;

    /**
     * @param page
     * @param sortOrder
     * @return oN based on the order and page ,
     * Data being filter and send back
     * Default Sorting is asc
     * <p>
     * Also getting the paidAmount from children records
     * and assigning to parent based on parentId
     */
    public List<Parent> getParents(int page, String sortOrder) {
        List<Parent> result;
        try {
            log.info("Pulling parents for page : " + page + " and sortOrder : " + sortOrder);
            List<Parent> parents = database.getParents();
            int pageSize = 2;
            if (page <= 0) {
                page = 1;
            }
            int skip = ((page - 1) * pageSize);

            if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                parents.sort(Comparator.comparing(Parent::getId).reversed());
            } else {
                parents.sort(Comparator.comparing(Parent::getId));
            }

            result = parents.stream().skip(skip).limit(pageSize)
                    .peek(e -> e.setTotalPaidAmount(
                            database.getChildrenByParentId(e.getId()).stream().map(Child::getPaidAmount).reduce(0L, Long::sum))
                    ).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
        log.info("Completed pulling data for parents");
        return result;
    }

    /**
     * @param parentId
     * @return Pulling children based on parentId on sorted on children id
     */
    public List<Child> getChildrenByParentId(Long parentId) {
        List<Child> result;
        log.info("Pulling children for parentId: " + parentId);
        try {
            result = database.getChildrenByParentId(parentId);
            result.sort(Comparator.comparing(Child::getId));
        } catch (Exception e) {
            throw e;
        }
        log.info("Completed pulling data for children");
        return result;
    }
}
