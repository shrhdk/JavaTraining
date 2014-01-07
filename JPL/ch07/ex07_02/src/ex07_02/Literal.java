package ex07_02;

public class Literal {

	// boolean boolean1 = 1;		// Error
	// boolean boolean2 = "true"	// Error
	boolean boolean3 = true;
	boolean boolean4 = false;
	
	// byte byte1 = -129;			// Error
	byte byte2 = -128;
	byte byte3 = 127;
	// byte byte4 = 128;			// Error
	// byte byte5 = 1L;				// Error
	
	// short short1 = -32769;		// Error
	short short2 = -32768;
	short short3 = 32767;
	// short short4 = 32768;		// Error
	// short short5 = 1L;			// Error
	
	int int1 = 1;
	// int int2 = 1L;				// Error
	// int int3 = 1.0f;				// Error
	// int int4 = 1.0;				// Error
	
	// long long1 = -9223372036854775809L;	// Error
	long long2 = -9223372036854775808L;
	long long3 = 9223372036854775807L;
	// long long4 = 9223372036854775808L;	//Error
	long long5 = 1;
	// long long6 = 1.0f;			// Error
	// long long7 = 1.0;			// Error
	
	float float1 = 1;
	float float2 = 1L;
	float float3 = 1.0f;
	// float float4 = 1.0;			// Error
	
	double double1 = 1;
	double double2 = 1L;
	double double3 = 1.0f;
	double double4 = 1.0;
}
