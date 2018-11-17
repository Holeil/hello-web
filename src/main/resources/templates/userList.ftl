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
                        <th scope="col">Choose</th>
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
                        <td><input type="checkbox" name="${user.getUsername()}"/></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <div class="btn-group" role="group" aria-label="Basic example">
                <div class="btn btn-secondary" role="button">Choose all</div>
                <a class="btn btn-secondary" href="#" role="button">Block users</a>
                <a class="btn btn-secondary" href="#" role="button">Unblock users</a>
                <a class="btn btn-secondary" href="#" role="button">Delete users</a>
            </div>
        </div>
    </div>
</@common.page>