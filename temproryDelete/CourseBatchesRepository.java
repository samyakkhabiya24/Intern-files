package com.db.courseproject.repositry;

import java.util.List;

import com.db.courseproject.classes.CourseBatches;

public interface CourseBatchesRepository {
    
    public List<CourseBatches> findAll();
    
    public List<CourseBatches> findByCourseId(Long courseId);

    public CourseBatches findById(Long id);

    public void save(CourseBatches courseBatch);

    public void update(CourseBatches courseBatch);

    public void deleteById(Long id);
    
}
