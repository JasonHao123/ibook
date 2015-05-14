package jason.app.ibook.job.api.dao;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;

import java.util.List;

public interface IDepartmentDao {
    Department create(Department department);

    List<Department> findByCompanyId(Long id);
}
