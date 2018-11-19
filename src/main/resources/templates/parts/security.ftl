<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isUser =  user.isRole("USER")
        isAdmin = user.isRole("ADMIN")
        userId = user.id
    >
<#else>
    <#assign
        name = "unknown"
        userId = -1
        user = false
        isUser = false
        isAdmin = false
    >
</#if>