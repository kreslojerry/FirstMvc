<#include "main-template.ftl"/>

<#macro content>
    <#if users?has_content>
        <#list users as user>
            ${user.id} &nbsp; ${user.name} &nbsp; ${user.surname} &nbsp; ${user.email}
            <a href="/users/${user.id}/edit">Редактировать</a>
            <label></label>
            <a href="/users/${user.id}/delete">Удалить</a>
            <br/>
        </#list>
    <#else>
        <p>Пользователей пока нет</p>
    </#if>
    <hr/>
    <a href="/logout">Logout</a>
</#macro>

<@main title="All users"/>
