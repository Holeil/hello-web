<#import "parts/common.ftl" as common>

<@common.page>
    <form action="/updateuserlist" method="post">
        <div class="content">
            <h2>Список пользователей</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Username</th>
                        <th scope="col">Роли</th>
                        <th scope="col">Выбор пользователя</th>
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
                <input type="button" class="btn btn-secondary" name="choose-all-users" value="Выбрать всех" id="choose-all-users">
                <input type="submit" class="btn btn-secondary" name="block-users" value="Заблокировать">
                <input type="submit" class="btn btn-secondary" name="unblock-users" value="Разблокировать">
                <input type="submit" class="btn btn-secondary" name="set-admin" value="Сделать админом">
                <input type="submit" class="btn btn-secondary" name="delete-users" value="Удалить">
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>
<script src="/script/checkAllUsers.js"></script>
</@common.page>