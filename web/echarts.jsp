<%--
  Created by IntelliJ IDEA.
  User: Aimurn
  Date: 2023/6/27
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>ECharts</title>
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="js/echarts.js"></script>
    <script src="js/jquery.js"></script>
</head>
<body>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script>
        // 基于准备好的dom，初始化echarts实例
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        $.getJSON("echarts",function (data) {

            // 指定图表的配置项和数据
            option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data: ['销量']
                },
                xAxis: {
                    data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子', 'XXX']
                },
                yAxis: {},
                series: [
                    {
                        name: '销量',
                        type: 'bar',
                        data: data.data1
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        });
    </script>
</body>
</html>