# TestNgDemo

Selenium WebDriver + TestNG 自動化測試框架，
主要練習：
1. 支援多瀏覽器平行測試
2. 多環境切換
3. ExtentReports 報告產出
4. Jenkins CI 整合。

## 專案版本號
V0.0.1


| 技術 | 版本 | 用途 |
|------|------|------|
| Java | 17 | 開發語言 |
| Selenium WebDriver | 4.18.1 | 瀏覽器自動化 |
| TestNG | 7.9.0 | 測試框架 |
| WebDriverManager | 5.7.0 | 自動管理瀏覽器驅動 |
| ExtentReports | 5.1.2 | HTML 測試報告 |
| Log4j2 | 2.23.0 | 日誌管理 |
| Maven | - | 建置工具 |

## 專案結構

```
src/
├── main/
│   ├── java/com/framework/
│   │   ├── config/
│   │   │   ├── ConfigManager.java            # Singleton 設定管理，讀取 properties
│   │   │   └── EnvironmentConfig.java        # 環境設定便捷存取 (baseUrl, browser, headless)
│   │   ├── constants/
│   │   │   ├── FrameworkConstants.java        # 路徑、Key 等常數
│   │   │   └── TimeoutConstants.java         # 超時預設值
│   │   ├── driver/
│   │   │   ├── DriverFactory.java            # Factory Pattern 建立 WebDriver
│   │   │   └── DriverManager.java            # ThreadLocal 管理 WebDriver (平行安全)
│   │   ├── enums/
│   │   │   ├── BrowserType.java              # CHROME / EDGE / FIREFOX
│   │   │   ├── EnvironmentType.java          # DEV / SIT / PROD
│   │   │   └── WaitStrategy.java             # CLICKABLE / VISIBLE / PRESENT / NONE
│   │   ├── exceptions/
│   │   │   ├── FrameworkException.java       # 基礎 RuntimeException
│   │   │   ├── BrowserNotSupportedException.java
│   │   │   ├── EnvironmentNotSupportedException.java
│   │   │   └── PropertyFileNotFoundException.java
│   │   ├── listeners/
│   │   │   ├── ExtentReportListener.java     # ExtentReports 生命週期 + 失敗截圖
│   │   │   ├── TestListener.java             # Log4j2 測試事件日誌
│   │   │   ├── RetryAnalyzer.java            # 失敗自動重試
│   │   │   └── RetryTransformer.java         # 自動綁定 RetryAnalyzer 至所有測試
│   │   ├── pages/
│   │   │   ├── BasePage.java                 # Page Object 基礎類 (click/sendKeys/getText/wait)
│   │   │   ├── LoginPage.java               # 登入頁 Page Object
│   │   │   └── ShoppingPage.java            # 購物頁 Page Object
│   │   └── utils/
│   │       ├── WaitUtils.java                # waitForPageLoad / waitForJQuery
│   │       ├── ScreenshotUtils.java          # 截圖工具
│   │       ├── LogUtils.java                 # Log4j2 + ExtentReport 雙重日誌
│   │       └── DateUtils.java                # 時間格式化
│   └── resources/
│       ├── log4j2.xml                        # Log4j2 設定 (Console + RollingFile)
│       └── config/
│           ├── config.properties             # 全域設定 (browser, environment, headless)
│           ├── dev.properties                # DEV 環境 base.url
│           ├── sit.properties                # SIT 環境 base.url
│           └── prod.properties               # PROD 環境 base.url
├── test/
│   ├── java/
│   │   ├── com/framework/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java             # @BeforeMethod/@AfterMethod 管理 driver
│   │   │   └── eshop/
│   │   │       └── LoginPageTest.java        # 登入流程測試
│   │   ├── practice/                         # TestNG 學習範例
│   │   │   ├── config/Listeners.java
│   │   │   └── smoke/
│   │   │       ├── Login/loginPage.java
│   │   │       └── Logout/logoutPage.java
│   │   └── skbank/                           # SKBank 學習範例
│   │       └── smoke/
│   │           ├── Creditcard/homePage.java
│   │           └── Loan/houseLoanPage.java
│   └── resources/
│       └── testdata/                         # 測試資料目錄
│
testng-framework.xml          # 框架主套件 (含 listeners)
testng-smoke.xml              # 快速冒煙測試套件
testng-cross-browser.xml      # 三瀏覽器平行測試套件
testng-by-group.xml           # [學習] 依 group 執行
testng-by-package.xml         # [學習] 依 package 執行
testng-by-parallel.xml        # [學習] 平行執行
testng-by-parameter.xml       # [學習] 參數化
testng-class-method.xml       # [學習] 指定 class/method
testng-in-listeners.xml       # [學習] listener 示範
```

## 設計模式

| 模式 | 類別 | 目的 |
|------|------|------|
| Singleton | `ConfigManager` | 設定檔只載入一次，跨執行緒安全 |
| ThreadLocal | `DriverManager` | 平行執行時每個執行緒有獨立 WebDriver |
| Factory | `DriverFactory` | 依據瀏覽器類型建立對應 driver |
| Page Object Model | `BasePage` | UI 操作與測試邏輯分離 |
| Strategy | `WaitStrategy` | 靈活選擇等待策略 |

## 設定優先順序

瀏覽器與環境設定的覆蓋優先順序（由高到低）：

```
TestNG XML <parameter>  >  System Property (-D)  >  env.properties  >  config.properties
```

## 使用方式

### 基本執行

```bash
# 使用預設設定 (chrome + dev)
mvn clean test

# 指定 suite
mvn clean test -Dsuite.xml=testng-smoke.xml
```

### 切換瀏覽器

```bash
mvn clean test -Dbrowser=edge
mvn clean test -Dbrowser=firefox
```

### 切換環境

```bash
mvn clean test -Denvironment=sit
mvn clean test -Denvironment=prod
```

### Headless 模式

```bash
mvn clean test -Dheadless=true
```

### 跨瀏覽器平行測試

一次指令同時跑 Chrome、Edge、Firefox：

```bash
mvn clean test -Dsuite.xml=testng-cross-browser.xml
```

### Maven Profiles

```bash
mvn clean test -Pdev          # DEV 環境
mvn clean test -Psit          # SIT 環境
mvn clean test -Pprod         # PROD 環境 (自動 headless)
mvn clean test -Pci           # CI 模式 (headless + smoke suite)
```

### Jenkins 整合

```bash
mvn clean test -Dbrowser=chrome -Denvironment=sit -Dheadless=true -Dsuite.xml=testng-smoke.xml
```

## 報告與產出

| 產出 | 路徑 |
|------|------|
| ExtentReports HTML 報告 | `reports/extent-reports/TestReport_{timestamp}.html` |
| 失敗截圖 | `reports/screenshots/failed/` |
| Log4j2 日誌 | `logs/test-automation.log` |
| TestNG 預設報告 | `test-output/` |

## 如何新增測試

### 1. 建立 Page Object (`src/main/java`)

```java
public class MyPage extends BasePage {
    private final By SOME_BUTTON = By.id("btn");

    public MyPage clickSomeButton() {
        click(SOME_BUTTON);
        return this;
    }
}
```

### 2. 建立 Test Class (`src/test/java`)

```java
public class MyTest extends BaseTest {
    @Test
    public void testSomething() {
        getDriver().get(EnvironmentConfig.getBaseUrl());
        MyPage page = new MyPage();
        page.clickSomeButton();
        Assert.assertTrue(...);
    }
}
```

### 3. 加入 TestNG XML

```xml
<test name="My Tests">
    <classes>
        <class name="com.framework.tests.MyTest"/>
    </classes>
</test>
```
