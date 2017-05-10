package com.mycompany.filesmanipulator.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 *
 * @author andrew
 */
@Accessor
public interface FileNoSqlAccestor {
    @Query("select * from filesmanipulator.file_statistics where name=:n ALLOW FILTERING")
    ResultSet selectByName(@Param("n") String name);
}
