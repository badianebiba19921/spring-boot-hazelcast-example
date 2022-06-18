package com.techgeeknext.ebb.dao.impl;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employee " +
                "(empId, empName) VALUES (?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                emp.getEmpId(), emp.getEmpName()
        });
    }

    @Override
    public void insertEmployees(final List<Employee> employees) {
        String sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee employee = employees.get(i);
                ps.setString(1, employee.getEmpId());
                ps.setString(2, employee.getEmpName());
            }
            public int getBatchSize() {
                return employees.size();
            }
        });

    }

    @Override
    public List<Employee> getAllEmployees(){
        System.out.println("EmployeeDao - Get All Employees");
        String sql = "SELECT * FROM employee";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Employee> result = new ArrayList<Employee>();
        for(Map<String, Object> row:rows){
            Employee emp = new Employee();
            emp.setEmpId((String)row.get("empId"));
            emp.setEmpName((String)row.get("empName"));
            result.add(emp);
        }
        return result;
    }

    @Override
    public Employee getEmployeeById(String empId) {
        String sql = "SELECT * FROM employee WHERE empId = ?";
        return (Employee)getJdbcTemplate().queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>(){
            @Override
            public Employee mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Employee emp = new Employee();
                emp.setEmpId(rs.getString("empId"));
                emp.setEmpName(rs.getString("empName"));
                return emp;
            }
        });
    }
}