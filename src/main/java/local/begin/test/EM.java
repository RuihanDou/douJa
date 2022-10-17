package local.begin.test;

//object EMModelData {
//
//        /**
//         * 目标: 解决位置偏置对特征及模型的影响
//         * 核心解释: ISL : item sample list. 用于计算feature部分, 代表预测前的
//         * Q(l_index, IC) : l_index 为展示位置, IC 为 item ctr 代表item本身的能力. 简化当前函数为 Q(l_index, IC) = Q(l_index) * IC
//         * IC = ISL.map(_.label / Q(_.l_index)).sum / ISL.size  // 因为 l_index 会改变item和最终label的关系， 故需要原油label进行反响缩放
//         * 那么目标可拟合为以下不等式 Q(l_index) * IC = Label
//         * 通过等式的转换, 我们将原有的基于item能力预估纠正转变为基于先验label对当前label的预估.那么等式进步部变换
//         * ISL-i 等价于 ISL中 index=i的集合 那么预估变为 Q(0) * IC-0 = Label,  Q(1) * IC-1 = Label...
//         * 继续等式转换。 将feature端的位置聚合, 加权端的位置聚合, label 聚合， 得到新的不等式
//         * 对于同一item数据，按照index拆分成多个label, 每个权重为 ISL-i.size/ ISL.size. = Wji
//         * 限定ISL-i取值范围, 将少单挑label的拆分, 同一item最终标签的权重为 Wji. 那么聚合Wji 是label的权重. 同时得到形影的自此位置的真实点击率
//         * group by feature的 f_index 和 l_index , 则 可以得到相应的平均点击率和相应的权重。那么最终的表达式变更为
//         * CTR[i] / Q(f_index) * Q(l_index) = label , weight 得到我们想要的sample, 求解Q(l_index)的关系
//         */
//
//        def main(args: Array[String]): Unit = {
//
//        val spark = SparkSession.builder().
//        appName("EMModelData").
//        config("spark.sql.warehouse.dir", "hdfs://mfwCluster/user/hive/warehouse").config("mapred.output.compress", "false").
//        enableHiveSupport().
//        getOrCreate()
//
//import spark.implicits._
//
//    var startTime = CommonUtils.getYesterdayStr()
//            if (args.length > 0) {
//            startTime = args(0)
//            }
//            val timeStr = startTime
//
//            val startTime_15 = CommonUtils.getBeforeDaysStr(timeStr, 15)
//            val startTime_1 = CommonUtils.getBeforeDaysStr(timeStr, 1)
//
//            val log = BaseDataUtil.getIndexFlowDataFilterQaUserUsefulCol(spark, startTime_15, timeStr).
//            filter("biz_line = 'rec'").
//            select(
//            "item_id",
//            "item_type",
//            "pageScene",
//            "is_click_event",
//            "pos_index",
//            "dt").na.drop().
//            repartition(2000).persist(StorageLevel.MEMORY_AND_DISK_SER)
//
//            val sExpr = Seq(
//            "is_click_event as label",
//            "if(pos_index is not null and pos_index >= 0 and pos_index <=60, pos_index, 61) as pos_index",
//            "concat(item_id, '_' ,item_type) as item_key"
//            )
//
//            val scenes = Array("doubleFlow", "near", "mdd", "poi_detail", "weng_detail")
//            scenes.foreach(scene => {
//
//            val features = log.filter("pageScene = '%s'".format(scene)).
//            filter("dt <= '%s'".format(startTime_1)).selectExpr(sExpr: _*)
//            val label = log.filter("pageScene = '%s'".format(scene)).
//            filter("dt > '%s'".format(startTime_1)).selectExpr(sExpr: _*)
//
//            val featureGDF = features.
//            withColumnRenamed("pos_index", "feature_index").
//            groupBy("item_key", "feature_index").
//            agg(sum("label").as("label_sum"), count("label").as("label_count")).
//            withColumn("feature_index_ctr", col("label_sum") / col("label_count"))
//            val itemG = featureGDF.groupBy("item_key").agg(sum("label_count").as("item_count"))
//
//            val baseSample_ = label.join(featureGDF, Seq("item_key")).join(itemG, Seq("item_key")).
//            withColumn("label_weight", col("label_count") / col("item_count")).
//            withColumn("feature_index_ctr_power_weight", col("label_weight") * col("feature_index_ctr")).
//            withColumn("label_power_weight", col("label") * col("label_weight"))
//
//            val baseSample = baseSample_.
//            groupBy("feature_index", "pos_index").agg(
//            (sum("feature_index_ctr_power_weight") / sum("label_weight")).as("feature_ctr"),
//            (sum("label_power_weight") / sum("label_weight")).as("label_ctr"),
//            sum("label_weight").as("weight")
//            ).persist(StorageLevel.MEMORY_AND_DISK_SER)
//            baseSample.write.mode("overwrite").save("/user/algo/rec/rank/EMModelData/df/" + scene + "/" + startTime)
//
//            /** tfrecord */
//            val tmpTFRecord = baseSample.rdd.map(
//            row => {
//            val ctr = row.getAs[Double]("feature_ctr").toFloat
//            val featureIndex = row.getAs[Int]("feature_index")
//            val labelIndex = row.getAs[Int]("pos_index")
//            val label = row.getAs[Double]("label_ctr").toFloat
//            val weight = row.getAs[Double]("weight").toFloat
//            val ids = Array(
//            (featureIndex - 1) / 12 + 1,
//            featureIndex % 12,
//            featureIndex,
//            (labelIndex - 1) / 12 + 1,
//            labelIndex % 12,
//            labelIndex
//            )
//            (ids, Array(ctr), label, weight)
//            }
//            ).toDF("if", "nf", "label", "weight")
//
//            tmpTFRecord.repartition(10).write.mode("overwrite").format("tfrecord").
//            option("recordType", "Example").
//            option("codec", "org.apache.hadoop.io.compress.GzipCodec").
//            save("/user/algo/rec/rank/EMModelData/tf/" + scene + "/" + startTime)
//            })
//            }
//
//            def getPositionBias(spark: SparkSession, pageScene: String, train_date: String, test_date: String): String = {
//
//            val scene = pageScene match {
//            case "rec" => "doubleFlow"
//            case "doubleFlow" => "doubleFlow"
//            case "mdd" => "mdd"
//            case "near" => "near"
//            case "mdd" => "mdd"
//            case "mdd" => "mdd"
//            case "poi" => "poi_detail"
//            case "weng" => "weng_detail"
//            case "wengDetail" => "weng_detail"
//            case "weng_detail" => "weng_detail"
//            }
//
//            val train_df = spark.read.parquet("/user/algo/rec/rank/EMModelData/df/%s/%s".format(scene, train_date))
//            val test_df = spark.read.parquet("/user/algo/rec/rank/EMModelData/df/%s/%s".format(scene, test_date))
//
//            def transformData(data: DataFrame) = {
//            data.rdd.map(
//            row => {
//            (
//            row.getAs[Double]("feature_ctr"),
//            row.getAs[Int]("feature_index"),
//            row.getAs[Int]("pos_index"),
//            row.getAs[Double]("label_ctr"),
//            row.getAs[Double]("weight")
//            )
//            }
//            ).collect()
//            }
//
//            val train_data = transformData(train_df)
//            val test_data = transformData(test_df)
//
//            val position_bias = scala.collection.mutable.ArrayBuffer[Double]()
//            (0 to 61).foreach(_ => position_bias.append(1d))
//            position_bias(1) = 1.5 //固定position_bias(1)的值为1.5
//            val rate = 0.01d
//
//            var min_loss = 1000000d
//            var loss = 1000000d
//            var epoch = 0
//
//            while (loss <= min_loss && epoch < 5000) {
//            min_loss = loss
//            epoch = epoch + 1
//            train_data.map(line => {
//            val (fctr, fi, li, lctr, weight) = line
//            val fi_bias = position_bias(fi)
//            val li_bias = position_bias(li)
//            val loss_grad = 2 * (fctr / fi_bias * li_bias - lctr)
//            val li_bias_grad = fctr / fi_bias * loss_grad
//            val fi_bias_grad = -fctr * li_bias * loss_grad / fi_bias / fi_bias
//            if (fi != 1) position_bias(fi) = position_bias(fi) - rate * fi_bias_grad * weight / 100
//            if (li != 1) position_bias(li) = position_bias(li) - rate * li_bias_grad * weight / 100
//            })
//
//            loss = test_data.map(line => {
//            val (fctr, fi, li, lctr, weight) = line
//            val fi_bias = position_bias(fi)
//            val li_bias = position_bias(li)
//            val p_lctr = fctr / fi_bias * li_bias
//            val loss_one = (p_lctr - lctr) * (p_lctr - lctr) * weight
//            loss_one
//            }).sum / test_data.map(_._5).sum
//            if (epoch % 50 == 0) println("epoch = %s , loss = %s".format(epoch, loss))
//            }
//
//            print(pageScene,train_date)
//            (0 to 61).foreach(i => println("index=%s, pos_bias=%s".format(i, position_bias(i))))
//            position_bias.slice(0, 61).mkString(",")
//            }
//
//            def evaluateFunction(spark: SparkSession) = {
//            val tmp = Array(1.2582669,
//            1.4035885,
//            1.255366,
//            1.3257537,
//            1.104883,
//            1.1856432,
//            1.1211817,
//            1.1356776,
//            1.1215405,
//            1.1131628,
//            1.2336888,
//            1.1917415,
//            0.91445076,
//            0.9706299,
//            0.9410728,
//            0.9321159,
//            0.883816,
//            0.8773406,
//            0.8735901,
//            0.8491189,
//            0.85772383,
//            0.8412806,
//            0.92970794,
//            0.90281576,
//            0.85276794,
//            0.9176009,
//            0.89841974,
//            0.87324893,
//            0.8282915,
//            0.83689463,
//            0.8128995,
//            0.820544,
//            0.8045114,
//            0.82209414,
//            0.89053726,
//            0.87421435,
//            0.82848626,
//            0.8927726,
//            0.8539387,
//            0.84436476,
//            0.8138615,
//            0.79707813,
//            0.7907456,
//            0.7758038,
//            0.7685992,
//            0.7957724,
//            0.8388394,
//            0.8403082,
//            0.8025589,
//            0.8525214,
//            0.78900284,
//            0.82428217,
//            0.7912701,
//            0.7711916,
//            0.77141124,
//            0.7686436,
//            0.75846195,
//            0.79945827,
//            0.8325867,
//            0.8011044,
//            0.73855484)
//            val indexCross = (1 to 61).zip(tmp).map(x => {
//            x._1 -> x._2
//            }).toMap
//            val indexCrossUDF = udf((index: Int) => indexCross.getOrElse(index, 0.6530402981783967))
//
//import spark.implicits._
//
////    val labelDT = DateUtil.getYesterday()
//    val labelDT = "20210920"
//            val dates = (1 to 7).map(i => CommonUtils.getBeforeDaysStr(labelDT, i))
//            val labelPath = "/user/algo/rec/rank/trainData/rec/"
//            val recFilter = "cid in ('C0096', 'C0097', 'C0096')"
//            val sExpr = Seq(
//            "label",
//            "if(pos_index is not null and pos_index >= 1 and pos_index <=60, pos_index, 61) as pos_index",
//            "concat(item_id, '_' ,item_type) as item_key"
//            )
//            val features = spark.read.parquet(dates.map(i => labelPath + i): _*).where(recFilter).selectExpr(sExpr: _*).cache()
//            val label = spark.read.parquet(labelPath + labelDT).where(recFilter).selectExpr(sExpr: _*).cache()
//            val ctr = features.withColumn("crossN", indexCrossUDF(col("pos_index"))).
//            selectExpr("item_key", "label", "label/crossN as clabel").groupBy("item_key").agg(
//            avg("label").as("ctr"), avg("clabel").as("cctr")
//            )
//            val result = label.withColumn("crossN", indexCrossUDF(col("pos_index"))).
//            join(ctr, Seq("item_key")).withColumn("lcctr", col("cctr") * col("crossN")).cache()
//
//            val tt = Array("ctr", "cctr", "lcctr").flatMap(
//            name => {
//            (1 to 12).map(
//            i => {
//            val evaluator = new BinaryClassificationEvaluator().setLabelCol("label").setRawPredictionCol(name) setMetricName ("areaUnderROC")
//            val auc = evaluator.evaluate(result.where(s"pos_index=$i"))
//            print(name, i, auc)
//            (name, i, auc)
//            }
//            )
//
//            }
//            )
//            tt.foreach(println)
//            tt.groupBy(_._2).toArray.sortWith(_._1 <= _._1).foreach(t => {
//            println(t._1);
//            t._2.foreach(println)
//            })
//            }
//            }
