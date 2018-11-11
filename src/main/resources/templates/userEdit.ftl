<#import "parts/common.ftl" as common>

<@common.page>
User Editor
<form action="/changeUser" method="post">
    <label><input type="text" name="username" value="${user.username}" /></label>
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} />${role}</label>
    </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    <button type="submit">Save</button>
</form>

<form action="/blockUser" method="post">
    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    <button type="submit">BLOCK USER</button>
</form>

<form action="/deleteUser" method="post">
    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    <button type="submit">DELETE USER</button>
</form>

<form action="/unblockUser" method="post">
    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    <button type="submit">UNBLOCK USER</button>
</form>
</@common.page>