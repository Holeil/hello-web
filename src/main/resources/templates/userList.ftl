<#import "parts/common.ftl" as common>

<@common.page>
    <form action="/updateuserlist" method="post">
        <div class="content">
            <h2>${language.userList}</h2>
            <table class="table">
                <thead class="bg-warning">
                    <tr>
                        <th scope="col">${language.username}</th>
                        <th scope="col">${language.roles}</th>
                        <th scope="col">${language.chooseUser}</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                    <tr>
                        <td><a href="/profile/${user.username}">${user.getUsername()}</a></td>
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
                <input type="button" class="btn btn-secondary" name="choose-all-users" value="${language.chooseAll}" id="choose-all-users">
                <input type="submit" class="btn btn-secondary" name="block-users" value="${language.blocked}">
                <input type="submit" class="btn btn-secondary" name="unblock-users" value="${language.unblocked}">
                <input type="submit" class="btn btn-secondary" name="set-admin" value="${language.setAdmin}">
                <input type="submit" class="btn btn-secondary" name="delete-users" value="${language.delete}">
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>
<script src="/script/checkAllUsers.js"></script>
</@common.page>