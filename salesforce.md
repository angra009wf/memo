# apexの画面遷移
```
SaleseForceは画面毎にIDを持っているため、IDを指定してPageReferenceを生成してreturnすると、その画面に遷移できる。
元画面に戻る場合はIDを保持しておき、そのIDでPageReferenceを生成すれば元画面に戻れる。
※clasic、lightning共にこれでOK。

例）
public PageReference init() {
　empId = ApexPages.CurrentPage().getParameters().get('Id');
　return new Pagereference('/' + empId);
}

※※lightningの場合、キャシュが残っており、画面が更新されない。
ので、元画面に戻るだけであれば、↓のやり方がベター。

<apex:page controller="CreatePersonalHistoryController" action="{!init}">
    <script src="../../soap/ajax/31.0/connection.js" type="text/javascript"></script>
    <script src="../../soap/ajax/31.0/apex.js" type="text/javascript"></script>
    <script type="text/javascript">
        // URLパラメータ取得
        var urlParam = [];
        var pair = location.search.substring(1).split('&');
        for (var i = 0; pair[i]; i++) {
            var param = pair[i].split('=');
            urlParam[param[0]] = param[1];
        }
        // 遷移元を再描画
        if (typeof sforce !== 'undefined' && sforce.one) {		// lightning
            sforce.one.back(true);
        } else {												// clasic
            location.href = '/' + urlParam['Id'];
        }
    </script>
</apex:page>
```