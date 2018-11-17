<#import "parts/common.ftl" as common>

<@common.page>
    <div class="wrapper">
        <div class="content">
            <h2>List of Users</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Username</th>
                        <th scope="col">Role</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                    <tr>
                        <th scope="row">${user.getId()}</th>
                        <td><a href="/user/${user.id}">${user.getUsername()}</a></td>
                        <td>
                            <#list user.roles as role>
                                ${role} <#sep>,
                            </#list>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <a class="btn btn-outline-primary btn-sm" href="#" role="button">Delete users</a>
        </div>
    </div>
</@common.page>