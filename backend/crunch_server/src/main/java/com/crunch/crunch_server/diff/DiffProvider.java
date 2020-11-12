package com.crunch.crunch_server.diff;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;

import lombok.var;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class DiffProvider {
    
    public String getDiffStr(String left, String right, String label) throws Exception {

        var originalLines = Arrays.stream(left.split("\n")).collect(toList());
        var patch = DiffUtils.diff(left, right, null);
        var diffStrList = UnifiedDiffUtils.generateUnifiedDiff(label, label, originalLines, patch, 10);

        return String.join("\n", diffStrList);
    }
}
