<#import "parts/common.ftl" as common>

<@common.page>
    <form action="/user/changeUserList" method="post">
        <div class="content">
            <h2>List of Users</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Username</th>
                        <th scope="col">Role</th>
                        <th scope="col">Choose</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                    <tr>
                        <td><a href="/user/${user.id}">${user.getUsername()}</a></td>
                        <td>
                            <#list user.roles as role>
                                ${role} <#sep>,
                            </#list>
                        </td>
                        <td><label><input type="checkbox" class="toCheck" name="${user.getUsername()}"></label></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <div class="btn-group" role="group" aria-label="Basic example">
                <input type="button" class="btn btn-secondary" name="choose-all-users" value="Choose all" id="choose-all-users">
                <input type="submit" class="btn btn-secondary" name="block-users" value="Block users">
                <input type="submit" class="btn btn-secondary" name="unblock-users" value="Unblock users">
                <input type="submit" class="btn btn-secondary" name="set-admin" value="Add admin">
                <input type="submit" class="btn btn-secondary" name="delete-users" value="Delete users">
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>
<script src="/script/script.js"></script>
</@common.page>