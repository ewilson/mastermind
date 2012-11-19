(ns wilsonericn.cli-test
  (:use clojure.test
        wilsonericn.cli))

(run-all-tests)

(deftest converts-string-to-guess-vector 
  (is (= [:red :orange :yellow :green] (convert-input "ROYG")))
  (is (= [:blue :violet :red :orange] (convert-input "BVRO"))))

(deftest converts-lc-string-to-guess-vector 
  (is (= [:red :orange :yellow :green] (convert-input "royg")))
  (is (= [:blue :violet :red :orange] (convert-input "bvro"))))

(deftest converts-string-with-punct-to-guess-vector 
  (is (= [:red :orange :yellow :green] (convert-input "r:o,y;g")))
  (is (= [:blue :violet :red :orange] (convert-input "b.v-r|o"))))

(deftest converts-string-with-space-to-guess-vector 
  (is (= [:red :orange :yellow :green] (convert-input "r o y g")))
  (is (= [:blue :violet :red :orange] (convert-input "b\tv\nr o"))))

(deftest displays-round-info
  (is (= 
"--------------
|RYGB|  |XXO |" 
(convert-output {:guess [:red :yellow :green :blue], :clue [:black :black :white]})))
  (is (= 
"----------------
|RYGBB|  |XXO  |" 
(convert-output {:guess [:red :yellow :green :blue :blue], :clue [:black :black :white]}))))