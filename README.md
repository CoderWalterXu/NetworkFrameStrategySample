# NetworkFrameStrategySample
策略模式，切换网络框架，支持Volley,OkHttp


In Application：

HttpHelper.init(new VolleyProcessor(this));

HttpHelper.init(new OkHttpProcessor());
