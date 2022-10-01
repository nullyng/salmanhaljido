const { useEffect, useRef, useCallback } = require("react");

function useScrollCount(dom, end, start = 0, duration = 1000) {
  const el = useRef();
  const stepTime = Math.abs(Math.floor(duration / (end - start)));

  const handleScroll = useCallback(([entry]) => {
    const { current } = el;

    if (entry.isIntersecting) {
      let currentNumber = start;
      const counter = setInterval(() => {
        currentNumber += 1;
        current.innerText = currentNumber;

        if (currentNumber === end) {
          clearInterval(counter);
        }
      }, stepTime);
    }
  }, []);

  useEffect(() => {
    let observer;
    const { current } = dom;

    if (current) {
      observer = new IntersectionObserver(handleScroll, { threshold: 0.1 });
      observer.observe(current);

      return () => observer && observer.disconnect();
    }
  }, [handleScroll]);

  return {
    ref: el,
  };
}

export default useScrollCount;
