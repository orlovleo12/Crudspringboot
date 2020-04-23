package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(String role);
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> listRoles();
}