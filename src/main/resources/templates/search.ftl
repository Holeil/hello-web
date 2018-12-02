<#list messages.content as message>
    <a href="/message/${message.messageId}">${message.title}</a>
</#list>