<#import "parts/common.ftl" as common>

<@common.page>
    <form action="/user/change" method="post">
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
                        <td><input type="checkbox" class="toCheck" name="${user.getUsername()}"/></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-secondary" role="button" id="choose-all-users">Choose all</button>
                <button class="btn btn-secondary" name="block-users" role="button">Block users</button>
                <button class="btn btn-secondary" name="unblock-users" role="button">Unblock users</button>
                <button class="btn btn-secondary" name="delete-users" role="button">Delete users</button>
            </div>
        </div>
    </form>
</@common.page>