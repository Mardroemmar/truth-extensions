@API(status = Status.MAINTAINED)
@DefaultQualifier(
    value = NonNull.class,
    locations = {
        TypeUseLocation.PARAMETER,
        TypeUseLocation.FIELD,
        TypeUseLocation.RETURN,
    }
)
package dev.mardroemmar.truthext.currency;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.checkerframework.framework.qual.TypeUseLocation;
