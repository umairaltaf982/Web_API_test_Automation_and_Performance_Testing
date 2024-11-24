cd "F:\University Tasks\FAST-BSE-5B\SQE\Assignment#4\Web_API_test_Automation_and_Performance_Testing\load-testing"

jmeter -n ^
  -t scripts/RestfulAPI_TestPlan.jmx ^
  -l results/raw/results.jtl ^
  -e -o results/reports/HTMLReport ^
  -Jjmeter.save.saveservice.output_format=xml ^
  -Jjmeter.save.saveservice.response_data=true ^
  -Jjmeter.save.saveservice.samplerData=true ^
  -Jjmeter.save.saveservice.requestHeaders=true ^
  -Jjmeter.save.saveservice.responseHeaders=true ^
  -Jjmeter.save.saveservice.assertion_results_failure_message=true