package app.revanced.patches.youtube.video.information.fingerprints

import app.revanced.patcher.extensions.or
import app.revanced.patcher.fingerprint.MethodFingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal object MdxSeekFingerprint : MethodFingerprint(
    accessFlags = AccessFlags.PUBLIC or AccessFlags.FINAL,
    returnType = "Z",
    parameters = listOf("J", "L"),
    customFingerprint = { methodDef, _ ->
        // The instruction count is necessary here to avoid matching the relative version
        // of the seek method we're after, which has the same function signature as the
        // regular one, is in the same class, and even has the exact same 3 opcodes
        // at the end (invoke-virtual, move-result, return).
        methodDef.implementation!!.instructions.count() == 3
    }
)