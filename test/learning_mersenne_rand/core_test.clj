(ns learning-mersenne-rand.core-test
  (:use midje.sweet)
  (:use [learning-mersenne-rand.core :as rnd-funcs]))

(defn in-range?
  [lower-limit upper-limit num]
  (and (>= num lower-limit) (< num upper-limit)))

(facts
  "about MersenneTwister generator"

  (fact
    "using the same seed an equal random numbers sequence is generated"
    (let [seed 4357
          rng1 (make-mersenne-twister-rng seed)
          rng2 (make-mersenne-twister-rng seed)
          rand-sequence-1 (take 1000 (repeatedly #(rnd-funcs/double-in-0-1! rng1)))
          rand-sequence-2 (take 1000 (repeatedly #(rnd-funcs/double-in-0-1! rng2)))]
      rand-sequence-1 => rand-sequence-2))

  (fact
    "double-in-0-1! produces numbers in range [0, 1)"
    (let [rng (make-mersenne-twister-rng 4357)
          in-range-0-1? (partial in-range? 0 1)
          rand-sequence (take 1000 (repeatedly #(rnd-funcs/double-in-0-1! rng)))]
      rand-sequence => (has every? in-range-0-1?)))

  (fact
    "double-in-range! produces numbers in range [lower-limit, upper-limit)"
    (let [rng (make-mersenne-twister-rng 4357)
          lower-limit 5
          upper-limit 10
          in-range? (partial in-range? lower-limit upper-limit)
          rand-sequence (take 1000 (repeatedly #(rnd-funcs/double-in-range! rng lower-limit upper-limit)))]
      rand-sequence => (has every? in-range?)))

  (fact
    "select-random-element! gets a random element from a sequence"
    (let [rng (make-mersenne-twister-rng 4357)
          elements [1 3 5]]
      (rnd-funcs/select-random-element! rng elements) => 3
      (rnd-funcs/select-random-element! rng elements) => 1
      (rnd-funcs/select-random-element! rng elements) => 1
      (rnd-funcs/select-random-element! rng elements) => 1
      (rnd-funcs/select-random-element! rng elements) => 3
      (rnd-funcs/select-random-element! rng elements) => 3
      (rnd-funcs/select-random-element! rng elements) => 3
      (rnd-funcs/select-random-element! rng elements) => 5
      (rnd-funcs/select-random-element! rng elements) => 3
      (rnd-funcs/select-random-element! rng elements) => 1
      (rnd-funcs/select-random-element! rng elements) => 1)))
