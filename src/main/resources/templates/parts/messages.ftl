<#macro messages path isUpdateMessageForm>
    <script async src="https://imgbb.com/upload.js" data-auto-insert="viewer-links" data-sibling="download" data-palette="blue"></script>
    <form action="/profile/${user.username}/${path}<#if isUpdateMessageForm>${message.id}</#if>" method="post" class="mt-5">
        <h2>${language.addMessage}</h2>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="title" value="<#if isUpdateMessageForm>${message.title}</#if>" class="form-control" placeholder="${language.title}">
            </div>
            <div class="col">
                <input type="text" name="specialty" value="<#if isUpdateMessageForm>${message.specialty}</#if>" class="form-control" placeholder="${language.specialty}">
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <textarea class="form-control" name="text" placeholder="${language.text}" style="resize: none; min-height: 300px"><#if isUpdateMessageForm>${message.text}</#if></textarea>
                <download style="display: inline-block;"/>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="tag" value="<#if isUpdateMessageForm>${message.tag}</#if>" class="form-control " placeholder="${language.tags}">
            </div>
            <div class="col footer">
                <button type="submit" class="btn btn-primary"><#if isUpdateMessageForm>${language.update}</#if><#if !isUpdateMessageForm>${language.create}</#if></button>
            </div>
        </div>
        <#if isUpdateMessageForm><input type="hidden" name="messageId" value="${message.id}" /></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>