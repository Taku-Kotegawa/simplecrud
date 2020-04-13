<t:messagesPanel />
<form:form modelAttribute="usersForm" action="create">

  <table>
    <tbody>
      <tr>
        <th width="100px"><form:label path="uid">ユーザID</form:label></th>
        <td>
          <form:input path="uid" autocomplete="off" />
          <form:errors path="uid" />
        </td>
      </tr>
      <tr>
        <th><form:label path="pass">パスワード</form:label></th>
        <td>
          <form:input path="pass" autocomplete="off" />
          <form:errors path="pass" />
        </td>
      </tr>
      <tr>
        <th><form:label path="name">氏名</form:label></th>
        <td>
          <form:input path="name" autocomplete="off" />
          <form:errors path="name" />
        </td>
      </tr>
      <tr>
        <th><form:label path="mail">メール</form:label></th>
        <td>
          <form:input path="mail" autocomplete="off" />
          <form:errors path="mail" />
        </td>
      </tr>
      <tr>
        <th><form:label path="comment">コメント</form:label></th>
        <td>
          <form:textarea path="comment" rows="5" cols="30" autocomplete="off" />
          <form:errors path="comment" />
        </td>
      </tr>
    </tbody>
  </table>
  <a href="list">一覧に戻る</a>
  <input type="submit" name="confirm" value="確認" />
</form:form>
