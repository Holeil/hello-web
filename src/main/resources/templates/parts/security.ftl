<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isUser =  user.isRole("USER")
        isAdmin = user.isRole("ADMIN")
    >
<#else>
    <#assign
        name = "unknown"
        isUser = false
        isAdmin = false
    >
</#if>