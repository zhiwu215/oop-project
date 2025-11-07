package java.com.zhiwu.virtualteamprojectmanagementsystem;

import java.sql.Types;
import java.util.Collections;

// web/web-admin/test/com.xxx.xxx
public class GeneratorCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/color_workshop_v1_dev?useUnicode=true" +
                                  "&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai", "root", "3.1415926")
                         .globalConfig(builder -> {
                             builder.author("zhiwu") // 设置作者
                                    .outputDir("src/main/java"); // 指定输出目录
                         })
                         .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                             int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                             if (typeCode == Types.SMALLINT) {
                                 // 自定义类型转换
                                 return DbColumnType.INTEGER;
                             }
                             return typeRegistry.getColumnType(metaInfo);
                         }))
                         .packageConfig(builder -> builder.parent("com.zhiwu") // 设置父包名
                                                          .moduleName("mp") // 设置父包模块名
                                                          .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")) // 设置mapperXml生成路径
                         )
                         .strategyConfig(builder -> builder.addInclude("promotion","promotion_url") // 设置需要生成的表名
                                                           // .addTablePrefix("t_", "c_") // 设置过滤表前缀
                                                           .entityBuilder()
                                                           .enableLombok() // 开启lombok模型
                                                           .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                                                           .controllerBuilder()
                                                           .enableRestStyle() // 开启生成 @RestController 控制器
                         )
                         .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .execute();
    }
}
