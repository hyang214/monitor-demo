# 监控DEMO

## 0x00. 项目目的

尝试将SpringBoot项目的自定义指标和常见系统指标，推送到PushGateway。然后采集到Prometheus，最后由Grafana进行展示。

## 0x01. 项目功能

自定义了4个不同类型的自定义指标，分别是：
+ Counter: 接口调用次数指标，CallCounter
+ Gauge：资源剩余情况指标，ResourceGauge
+ Histogram：接口调用柱状图指标，CallTimecostHistogram
+ Summary：接口分位计算指标，CallTimecostSummary

## 0x02. JMeter调用

使用Jmeter工具，并发请求接口，使得系统指标有波动。

