<#macro login path>

    <form action="${path}" method="post" class="login-fields">
        <div><input type="text" name="username" required placeholder="User name" class="access-field"/></div>
        <div><input type="password" name="password" required placeholder="Password" class="access-field"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Sign In" class="button"/></div>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out" class="button" />
    </form>
</#macro>