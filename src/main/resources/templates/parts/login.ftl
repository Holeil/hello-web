<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Имя пользователя:</label>
        <div class="col-sm-6">
            <input type="text" name="username"
                   maxlength="16" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="Username"
                   pattern="^[A-Za-z0-9_]{1,16}$"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" maxlength="16"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password"
                   pattern="^[A-Za-z0-9_]{1,16}$"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Повторите пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password2" maxlength="16"
                       class="form-control ${(password2Error??)?string('is-invalid', '')}"
                       placeholder="Retype password" />
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       placeholder="some@some.com" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="d-flex bd-highlight col-sm-8">
        <#if !isRegisterForm><a href="/registration" class="mr-auto pt-2 bd-highlight">Регистрация нового пользователя</a></#if>
        <button class="btn btn-primary p-2 bd-highlight ml-auto" type="submit"><#if isRegisterForm>Создать<#else>Войти</#if></button>
    </div>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn <#if siteTheme??>${siteTheme.navbarButton}<#else>btn-primary</#if>" type="submit"><#if language??>${language.logout}<#else>Выйти</#if></button>
</form>
</#macro>