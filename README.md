# TestNgDemo

Selenium WebDriver + TestNG 自動化測試框架，
主要練習：
1. 支援多瀏覽器平行測試
2. 多環境切換
3. ExtentReports 報告產出
4. Jenkins CI 整合

## 專案版本號
V1.0.1

| 技術 | 版本 | 用途 |
|------|------|------|
| Java | 17 | 開發語言 |
| Selenium WebDriver | 4.18.1 | 瀏覽器自動化 |
| TestNG | 7.9.0 | 測試框架 |
| WebDriverManager | 5.7.0 | 自動管理瀏覽器驅動 (ChromeDriver / msedgedriver / geckodriver) |
| ExtentReports | 5.1.2 | HTML 測試報告 + 失敗截圖嵌入 |
| Log4j2 | 2.23.0 | 日誌管理 (Console + RollingFile) |
| Apache POI | 5.2.5 | Excel 測試資料讀取 |
| Jackson | 2.17.0 | JSON 測試資料讀取 |
| MySQL Connector | 8.3.0 | 資料庫測試資料讀取 |
| Maven | 3.8.4+ | 建置工具 |

## 專案結構

```
TestNgDemo/
├── jenkins/
│   └── Jenkinsfile                          # Jenkins Declarative Pipeline
├── src/
│   ├── main/
│   │   ├── java/com/framework/
│   │   │   ├── config/
│   │   │   │   ├── ConfigManager.java            # Singleton 設定管理，讀取 properties
│   │   │   │   └── EnvironmentConfig.java        # 環境設定便捷存取 (baseUrl, browser, headless)
│   │   │   ├── constants/
│   │   │   │   ├── FrameworkConstants.java        # 路徑、Key 等常數
│   │   │   │   └── TimeoutConstants.java         # 超時預設值
│   │   │   ├── driver/
│   │   │   │   ├── DriverFactory.java            # Factory Pattern 建立 WebDriver
│   │   │   │   └── DriverManager.java            # ThreadLocal 管理 WebDriver (平行安全)
│   │   │   ├── enums/
│   │   │   │   ├── BrowserType.java              # CHROME / EDGE / FIREFOX
│   │   │   │   ├── EnvironmentType.java          # DEV / SIT / PROD
│   │   │   │   └── WaitStrategy.java             # CLICKABLE / VISIBLE / PRESENT / NONE
│   │   │   ├── exceptions/
│   │   │   │   ├── FrameworkException.java       # 基礎 RuntimeException
│   │   │   │   ├── BrowserNotSupportedException.java
│   │   │   │   ├── EnvironmentNotSupportedException.java
│   │   │   │   └── PropertyFileNotFoundException.java
│   │   │   ├── listeners/
│   │   │   │   ├── ExtentReportListener.java     # ExtentReports 生命週期 + 失敗截圖
│   │   │   │   ├── TestListener.java             # Log4j2 測試事件日誌
│   │   │   │   ├── RetryAnalyzer.java            # 失敗自動重試
│   │   │   │   └── RetryTransformer.java         # 自動綁定 RetryAnalyzer 至所有測試
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java                 # Page Object 基礎類 (click/sendKeys/getText/wait)
│   │   │   │   ├── eshop/
│   │   │   │   │   ├── EshopCommonPage.java      # E-Shop 共用元件 (logout)
│   │   │   │   │   ├── LoginPage.java            # 登入頁
│   │   │   │   │   ├── ShoppingPage.java         # 購物頁
│   │   │   │   │   ├── CartPage.java             # 購物車頁
│   │   │   │   │   ├── OrderPage.java            # 下單頁
│   │   │   │   │   └── OrderSuccessPage.java     # 訂單成功頁
│   │   │   │   └── skbank/
│   │   │   │       ├── SkBankCommonPage.java     # SK Bank 共用元件
│   │   │   │       ├── SkBankSitemapPage.java    # 網站地圖頁
│   │   │   │       └── CarLoanMessageBroadPage.java  # 車貸訊息頁
│   │   │   └── utils/
│   │   │       ├── LogUtils.java                 # Log4j2 + ExtentReport 雙重日誌
│   │   │       ├── ScreenshotUtils.java          # 截圖工具
│   │   │       ├── WaitUtils.java                # waitForPageLoad / waitForJQuery
│   │   │       ├── DateUtils.java                # 時間格式化
│   │   │       ├── JsonDataUtils.java            # JSON 測試資料讀取
│   │   │       ├── ExcelDataUtils.java           # Excel 測試資料讀取
│   │   │       └── DatabaseUtils.java            # MySQL 測試資料讀取
│   │   └── resources/
│   │       ├── log4j2.xml                        # Log4j2 設定 (Console + RollingFile)
│   │       └── config/
│   │           ├── config.properties             # 全域設定 (browser, environment, headless)
│   │           ├── dev.properties                # DEV 環境 base.url
│   │           ├── sit.properties                # SIT 環境 base.url
│   │           ├── prod.properties               # PROD 環境 base.url
│   │           └── db.properties                 # 資料庫連線設定
│   └── test/
│       ├── java/
│       │   ├── com/framework/
│       │   │   ├── base/
│       │   │   │   └── BaseTest.java             # @BeforeMethod/@AfterMethod 管理 driver
│       │   │   ├── eshop/
│       │   │   │   ├── LoginPageTest.java        # 登入流程測試
│       │   │   │   ├── ShoppingPageTest.java     # 購物車測試
│       │   │   │   └── OrderPageTest.java        # 下單測試 (JSON/Excel/DB DataProvider)
│       │   │   └── skbank/
│       │   │       └── CarLoanMessageBroadPageTest.java  # 車貸訊息頁測試
│       │   ├── practice/                         # TestNG 學習範例
│       │   │   ├── config/Listeners.java
│       │   │   └── smoke/
│       │   │       ├── Login/loginPage.java
│       │   │       └── Logout/logoutPage.java
│       │   └── skbank/                           # SK Bank 學習範例
│       │       └── smoke/
│       │           ├── Creditcard/homePage.java
│       │           └── Loan/houseLoanPage.java
│       └── resources/
│           └── testdata/                         # 測試資料目錄 (JSON/Excel)
│
├── testng-framework.xml          # 框架主套件 (含 listeners)
├── testng-smoke.xml              # 快速冒煙測試套件
├── testng-cross-browser.xml      # 三瀏覽器平行測試套件
├── testng-by-group.xml           # [學習] 依 group 執行
├── testng-by-package.xml         # [學習] 依 package 執行
├── testng-by-parallel.xml        # [學習] 平行執行
├── testng-by-parameter.xml       # [學習] 參數化
├── testng-class-method.xml       # [學習] 指定 class/method
├── testng-in-listeners.xml       # [學習] listener 示範
├── reports/
│   ├── extent-reports/           # ExtentReports HTML 報告
│   └── screenshots/failed/       # 失敗截圖
├── logs/                          # Log4j2 日誌
└── pom.xml
```

## 設計模式

| 模式 | 類別 | 目的 |
|------|------|------|
| Singleton | `ConfigManager` | 設定檔只載入一次，跨執行緒安全 |
| ThreadLocal | `DriverManager` | 平行執行時每個執行緒有獨立 WebDriver |
| Factory | `DriverFactory` | 依據瀏覽器類型建立對應 driver |
| Page Object Model | `BasePage` + 子類別 | UI 操作與測試邏輯分離 |
| Strategy | `WaitStrategy` | 靈活選擇等待策略 (CLICKABLE/VISIBLE/PRESENT/NONE) |
| Template Method | `BaseTest` | 標準化測試 setUp/tearDown 流程 |
| Listener | `ExtentReportListener` / `TestListener` | 事件驅動的報告與日誌 |

### Page Object 繼承架構

```
BasePage (abstract)
├── EshopCommonPage
│   ├── LoginPage
│   ├── ShoppingPage
│   ├── CartPage
│   ├── OrderPage
│   └── OrderSuccessPage
└── SkBankCommonPage
    ├── SkBankSitemapPage
    └── CarLoanMessageBroadPage
```

## 設定優先順序

瀏覽器與環境設定的覆蓋優先順序（由高到低）：

```
TestNG XML <parameter>  >  System Property (-D)  >  env.properties  >  config.properties  >  FrameworkConstants 預設值
```

## Data-Driven 測試資料來源

框架支援四種測試資料來源，透過 TestNG `@DataProvider` 整合：

| 來源 | 工具類別 | 範例 |
|------|---------|------|
| JSON 檔案 | `JsonDataUtils` | `testdata/eshop/orderData.json` |
| Excel 檔案 | `ExcelDataUtils` | `testdata/eshop/orderData.xlsx` |
| MySQL 資料庫 | `DatabaseUtils` | `db.properties` 設定連線 |
| 程式碼內建 | HashMap | 直接在 `@DataProvider` 中定義 |

## 使用方式

### 基本執行

```bash
# 使用預設設定 (Chrome + DEV)
mvn clean test

# 指定 suite
mvn clean test -Dsuite.xml=testng-smoke.xml
```

### 切換瀏覽器

```bash
mvn clean test -Dbrowser=CHROME
mvn clean test -Dbrowser=EDGE
mvn clean test -Dbrowser=FIREFOX
```

### 切換環境

```bash
mvn clean test -Denvironment=DEV
mvn clean test -Denvironment=SIT
mvn clean test -Denvironment=PROD
```

### Headless 模式

```bash
mvn clean test -Dheadless=true
```

### 組合參數

```bash
mvn clean test -Dbrowser=CHROME -Denvironment=SIT -Dheadless=true -Dsuite.xml=testng-smoke.xml
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

## 報告與產出

| 產出 | 路徑 | 說明 |
|------|------|------|
| ExtentReports HTML 報告 | `reports/extent-reports/TestReport_{timestamp}.html` | 含測試步驟、失敗截圖、系統資訊 |
| 失敗截圖 | `reports/screenshots/failed/{testName}_{timestamp}.png` | 測試失敗時自動擷取 |
| Log4j2 日誌 | `logs/test-automation.log` | Rolling File，最大 10MB x 10 檔 |
| TestNG 預設報告 | `test-output/` | TestNG 自動產生 |
| Surefire XML 報告 | `target/surefire-reports/*.xml` | Jenkins JUnit 整合用 |

## Jenkins CI/CD

### Pipeline 設定

Jenkins Job 使用 **Pipeline script from SCM**：

| 欄位 | 值 |
|------|------|
| SCM | Git |
| Repository URL | `https://github.com/openthedidi/TestNgDemo.git` |
| Branch Specifier | `*/${BRANCH}` |
| Script Path | `jenkins/Jenkinsfile` |

### Pipeline 參數（Jenkins UI 可選）

| 參數 | 類型 | 預設值 | 說明 |
|------|------|--------|------|
| BRANCH | string | main | Git 分支 |
| BROWSER | choice | CHROME | CHROME / EDGE / FIREFOX |
| ENVIRONMENT | choice | DEV | DEV / SIT / PROD |
| SUITE_XML | choice | testng-smoke.xml | TestNG Suite XML |
| HEADLESS | boolean | true | 是否 Headless 模式 |

### Pipeline Stages

```
Checkout → Build (mvn compile) → Test (mvn test) → Report → Post (JUnit + ExtentReport + 截圖歸檔)
```

### EC2 Amazon Linux 2023 前置安裝

```bash
# Git
sudo yum install -y git

# Java 17
sudo yum install -y java-17-amazon-corretto-devel

# Maven
sudo yum install -y maven

# Google Chrome
sudo dnf install -y \
  https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm

# Microsoft Edge
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo dnf config-manager --add-repo \
  https://packages.microsoft.com/yumrepos/edge
sudo dnf install -y microsoft-edge-stable

# Firefox
sudo dnf install -y firefox
```

### Jenkins Plugins

- Pipeline
- HTML Publisher（歸檔 ExtentReport HTML）
- JUnit（解析 Surefire XML 報告）

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
    @Test(groups = {"smoke"})
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
