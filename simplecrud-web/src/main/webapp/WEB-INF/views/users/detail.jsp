<t:messagesPanel />

  <table>
    <tbody>
      <tr>
        <th width="100px">ユーザID</th>
        <td>
          ${f:h(users.uid)}
        </td>
      </tr>
      <tr>
        <th>パスワード</th>
        <td>
          ${f:h(users.pass)}
        </td>
      </tr>
      <tr>
        <th>氏名</th>
        <td>
          ${f:h(users.name)}
        </td>
      </tr>
      <tr>
        <th>メール</th>
        <td>
          ${f:h(users.mail)}
        </td>
      </tr>

      <tr>
        <th>コメント</th>
        <td>
          ${f:h(users.comment)}
        </td>
      </tr>
      <tr>
        <th>ステータス</th>
        <td>
          ${f:h(users.status)}
        </td>
      </tr>    </tbody>
  </table>

<a href="list">一覧に戻る</a>
<form:form modelAttribute="usersForm" action="update" cssClass="inline">
  <form:hidden path="uid" value="${f:h(users.uid)}" />
  <input type="submit" name="form" value="編集" onclick="dis(this);" />
</form:form>
<form:form modelAttribute="usersForm" action="delete" cssClass="inline">
  <form:hidden path="uid" value="${f:h(users.uid)}" />
  <input type="submit" value="削除" onclick="dis(this);"/>
</form:form>

<br>

<script>
function dis(e) {
setTimeout(function(){e.disabled=true;},0);return true;
}
</script>
