package com.Group22.lab.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        //generateUser();
        //generateUserAccount();
        //generateShop();
        //generateShopAccount();
        //generateAllTradeRecord();
        //generateProduct();
    }
    private static void generateUser(){
            FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                    .globalConfig(builder -> {
                        builder.author("HouBinyang") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent("com.Group22.lab") // 设置父包名
                                .moduleName(null) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                    })
                    .strategyConfig(builder -> {
                        builder.entityBuilder().enableLombok();
                        builder.mapperBuilder().enableMapperAnnotation().build();
                        builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                                .enableRestStyle();  // 开启生成@RestController 控制器
                        builder.addInclude("user_data") // 设置需要生成的表名
                                .addTablePrefix("t_"); // 设置过滤表前缀
                    })
                    //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();
    }
    private static void generateShop(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                .globalConfig(builder -> {
                    builder.author("HouBinyang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.Group22.lab") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("shop_data") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    private static void generateUserAccount(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                .globalConfig(builder -> {
                    builder.author("HouBinyang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.Group22.lab") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("user_account") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    private static void generateShopAccount(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                .globalConfig(builder -> {
                    builder.author("HouBinyang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.Group22.lab") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("shop_account") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    private static void generateProduct(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                .globalConfig(builder -> {
                    builder.author("HouBinyang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.Group22.lab") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("product_data") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    private static void generateAllTradeRecord(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/LAB_2_DATABASE?serverTimezone=GMT%2b8", "root", "HBy20030925")
                .globalConfig(builder -> {
                    builder.author("HouBinyang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.Group22.lab") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C:\\Users\\21714\\Desktop\\2023_SE_Code\\lab3\\back-end\\demo\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("all_trade_record") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
