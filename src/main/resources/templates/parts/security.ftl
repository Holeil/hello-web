<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        userId = user.getId()
        name = user.getUsername()
        isUser =  user.isRole("USER")
        isAdmin = user.isRole("ADMIN")
    >
<#else>
    <#assign
        userId = -1
        name = "unknown"
        isUser = false
        isAdmin = false
    >
</#if>