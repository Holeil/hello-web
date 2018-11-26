<#macro messages path isUpdateMessageForm>
    <form action="/profile/${user.username}/${path}<#if isUpdateMessageForm>${message.id}</#if>" method="post" class="mt-5">
        <h2>Новый конспект</h2>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="title" value="<#if isUpdateMessageForm>${message.title}</#if>" class="form-control" placeholder="Название">
            </div>
            <div class="col">
                <input type="text" name="specialty" value="<#if isUpdateMessageForm>${message.specialty}</#if>" class="form-control" placeholder="Номер специальности">
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <textarea class="form-control" name="text" placeholder="Текст конспекта" style="resize: none; min-height: 300px"><#if isUpdateMessageForm>${message.text}</#if></textarea>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="tag" value="<#if isUpdateMessageForm>${message.tag}</#if>" class="form-control" placeholder="Тэги">
            </div>
            <div class="col footer">
                <button type="submit" class="btn btn-primary"><#if isUpdateMessageForm>Update</#if><#if !isUpdateMessageForm>Создать</#if></button>
            </div>
        </div>
        <#if isUpdateMessageForm><input type="hidden" name="messageId" value="${message.id}" /></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>