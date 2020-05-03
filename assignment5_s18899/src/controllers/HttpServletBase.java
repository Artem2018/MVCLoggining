package controllers;

import repositories.IUserRepository;
import repositories.UserRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class HttpServletBase extends HttpServlet {
    static final String RESOURCES = "RESOURCES";
    IUserRepository _repository;

    @Resource(name = "exmpl")
    private DataSource _dataSource;


    public void init() {
        _repository = new UserRepository(_dataSource);
    }
}
