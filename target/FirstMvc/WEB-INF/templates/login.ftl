<#include "main-template.ftl"/>

<#macro content>
    <form action="/login/process" method="post">
        <div>
            <label>Email</label>
            <input name="email" type="text"/>
        </div>
        <div>
            <label>Password</label>
            <input name="password" type="password"/>
        </div>
        <input type="submit" value="Login">
        <#if error??>
            <p>Пользователь с такими данными не найден</p>
        </#if>
    </form>
</#macro>

<@main title="Авторизация"/>
