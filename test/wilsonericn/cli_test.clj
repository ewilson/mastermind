(ns wilsonericn.cli-test
  (:use clojure.test
        wilsonericn.cli))

(run-all-tests)

(deftest converts-string-to-guess-vector 
  (is (= [:red :orange :yellow :green] (read-input "ROYG")))
  (is (= [:blue :violet :red :orange] (read-input "BVRO"))))

(deftest converts-lc-string-to-guess-vector 
  (is (= [:red :orange :yellow :green] (read-input "royg")))
  (is (= [:blue :violet :red :orange] (read-input "bvro"))))

(deftest converts-string-with-punct-to-guess-vector 
  (is (= [:red :orange :yellow :green] (read-input "r:o,y;g")))
  (is (= [:blue :violet :red :orange] (read-input "b.v-r|o"))))

(deftest converts-string-with-space-to-guess-vector 
  (is (= [:red :orange :yellow :green] (read-input "r o y g")))
  (is (= [:blue :violet :red :orange] (read-input "b\tv\nr o"))))
