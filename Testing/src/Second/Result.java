package Second;

public class Result {
    private int index;
    private boolean result;

    public Result() {
        index = 0;
        result = false;
    }

    public Result(String toParse) {
        String[] parsed = toParse.split(" ", 2);
        result = Boolean.parseBoolean(parsed[0]);
        index = Integer.parseInt(parsed[1]);
    }

    public Result(boolean aResult, int aIndex) {
        index = aIndex;
        result = aResult;
    }

    public int getIndex() {
        return index;
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        if (index != result1.index) return false;
        return result == result1.result;
    }

    @Override
    public int hashCode() {
        int result1 = index;
        result1 = 31 * result1 + (result ? 1 : 0);
        return result1;
    }

    @Override
    public String toString() {
        return result + " " + index;
     }
}
