let getRolesUserString = function (user) {
    let rolesUser = '';
    user.roles.forEach(function (role) {
        rolesUser = rolesUser + role.name + ' '
    });
    return rolesUser.trim()
}