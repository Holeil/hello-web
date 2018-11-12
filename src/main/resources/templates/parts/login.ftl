<#macro login path isRegisterForm>

    <form action="${path}" method="post" class="login-fields">
        <div class="form-group row">
            <label for="inputUsername3" class="col-sm-2 col-form-label">User name:</label>
            <div class="col-sm-6">
                <input type="text" id="inputUsername3" name="username" class="formal-control" placeholder="User name" required />
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" id="inputEmail3" name="email" class="formal-control" placeholder="Email" required />
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <label for="inputPassword3" class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" id="inputPassword3" name="password" class="formal-control" placeholder="Password" required />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign in</#if></button>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Sign out</button>
    </form>
</#macro>